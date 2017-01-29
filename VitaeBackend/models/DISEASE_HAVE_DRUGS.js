/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('DISEASE_HAVE_DRUGS', {
    disease_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'DISEASES',
        key: 'disease_id'
      }
    },
    drug_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'DRUGS',
        key: 'drug_id'
      }
    }
  }, {
    tableName: 'DISEASE_HAVE_DRUGS'
  });
};
