/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('BLOCK_REASON', {
    block_reason_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    description: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'BLOCK_REASON'
  });
};
