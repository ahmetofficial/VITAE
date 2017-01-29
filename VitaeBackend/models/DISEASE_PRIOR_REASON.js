/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('DISEASE_PRIOR_REASON', {
    disease_reason_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    reason_name: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'DISEASE_PRIOR_REASON'
  });
};
